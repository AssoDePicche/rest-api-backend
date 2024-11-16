import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.caravanas.util.Json;

public final class JsonTest {
  @Test
  void testKeyWithSingleValueFormatting() {
    Json json = new Json();

    json.put("A", "B");

    final String EXPECTED = "{\n\t\"A\": \"B\"\n}";

    assertTrue(json.toString().equals(EXPECTED));
  }

  @Test
  void testKeyWithTwoValuesFormatting() {
    Json json = new Json();

    json.put("A", "B");

    json.put("A", "C");

    final String EXPECTED = "{\n\t\"A\": [\n\t\t\"B\",\n\t\t\"C\"\n\t]\n}";

    assertTrue(json.toString().equals(EXPECTED));
  }
}
