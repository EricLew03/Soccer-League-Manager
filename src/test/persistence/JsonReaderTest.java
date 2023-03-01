package persistence;


import model.League;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            League wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

