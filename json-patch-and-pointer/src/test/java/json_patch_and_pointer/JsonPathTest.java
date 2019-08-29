package json_patch_and_pointer;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JsonPathTest {

  @Test
  void test() {

    DocumentContext jsonContext = JsonPath.parse(getClass().getClassLoader().getResourceAsStream("testObject.json"));

    String jsonpathCreatorNamePath = "$['tool']['jsonpath']['creator']['name']";
    String jsonpathCreatorName = jsonContext.read(jsonpathCreatorNamePath);
    Assertions.assertEquals("Jayway Inc.", jsonpathCreatorName);

    String jsonpathCreatorLocationPath = "$['tool']['jsonpath']['creator']['location'][*]";
    List<String> jsonpathCreatorLocation = jsonContext.read(jsonpathCreatorLocationPath);

    Assertions.assertEquals(3, jsonpathCreatorLocation.size());

  }

}
