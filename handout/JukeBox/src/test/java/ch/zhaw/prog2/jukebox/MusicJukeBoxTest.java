package ch.zhaw.prog2.jukebox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MusicJukeBoxTest {

    private JukeBox jukeBox;

    @Mock
    private Song mock;

    @BeforeEach
    public void setUp() throws Exception {
        jukeBox = new MusicJukeBox();
        MockitoAnnotations.initMocks(this);

        when(mock.getTitle()).thenReturn("Frozen");
        when(mock.isPlaying()).thenReturn(true);
        doNothing().when(mock).start();
    }

    @Test
    public void testPlayOfNoneExistingSong() {
        assertThrows(JukeBoxException.class, () -> jukeBox.playTitle("NoneExistingTitle"));
    }

    @Test
    public void testGetPlayList() {
        Song mockSong1 = mock(Song.class);
        when(mockSong1.getTitle()).thenReturn("Frozen");

        Song mockSong2 = mock(Song.class);
        when(mockSong2.getTitle()).thenReturn("Disney Classics");

        Song mockSong3 = mock(Song.class);
        when(mockSong3.getTitle()).thenReturn("Lion King");

        Song mockSong4 = mock(Song.class);
        when(mockSong4.getTitle()).thenReturn("Lady Gaga");

        jukeBox.addSong(mockSong1);
        jukeBox.addSong(mockSong2);
        jukeBox.addSong(mockSong3);
        jukeBox.addSong(mockSong4);
        jukeBox.addSong(mockSong1); // sollte nicht ein zweites Mal hinzugefügt werden

        List<Song> list = jukeBox.getPlayList();
        assertNotNull(list);
        assertEquals(4, list.size());

        verify(mockSong1, times(2)).getTitle();
        assertEquals("Frozen", mockSong1.getTitle());

        verify(mockSong2).getTitle();
        assertEquals("Disney Classics", mockSong2.getTitle());

        verify(mockSong3).getTitle();
        assertEquals("Lion King", mockSong3.getTitle());

        verify(mockSong4).getTitle();
        assertEquals("Lady Gaga", mockSong4.getTitle());
    }

    @Test
    public void testPlayMock() {
        jukeBox.addSong(mock);
        jukeBox.playTitle("Frozen");

        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).getTitle();
        inOrder.verify(mock).start();
    }

    @Test
    public void testPlayOfAlreadyPlayingSong() {
        Song mockSong1 = mock(Song.class);
        when(mockSong1.getTitle()).thenReturn("Frozen");

        jukeBox.addSong(mockSong1);
        try {
            jukeBox.playTitle("Frozen");
        } catch (JukeBoxException e) {
            System.err.println("No Exception expected here");
        }

        doThrow(new JukeBoxException("Song is already playing")).when(mockSong1).start();

        try {
            jukeBox.playTitle("Frozen");
        } catch (JukeBoxException e) {
            System.err.println("Song should not play yet. Song is already playing.");
        }

        verify(mockSong1, times(2)).start();
        verify(mockSong1, times(1)).getTitle();
    }

    @Test
    void testActualSong() {
        jukeBox.addSong(mock);
        jukeBox.playTitle("Frozen");
        assertEquals("Frozen", jukeBox.getActualSong().getTitle());

        verify(mock).start();
        verify(mock, times(2)).getTitle();
    }
}
