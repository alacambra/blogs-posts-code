package json_patch_and_pointer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.json.*;

public class PatchAndPointer {

  @Test
  void oldVsNewWay() {

    JsonObject jsonObject = Json.createReader(getClass().getClassLoader().getResourceAsStream("example.json")).readObject();

    String nameWithObject = jsonObject.getJsonArray("user_mentions").getJsonObject(0).getString("name");
    String nameWithPointer = ((JsonString) Json.createPointer("/user_mentions/0/name").getValue(jsonObject)).getString();


    Assertions.assertEquals(nameWithObject, nameWithPointer);
  }

  @Test
  void pointerDemo() {

    JsonObject example = Json.createReader(getClass().getClassLoader().getResourceAsStream("example.json")).readObject();

    /**
     * Get a value.
     * Pointer and patches must begin always with /
     * We still need to cast, since the only method fiven is getValue which returns JsonValue
     */
    JsonNumber id = ((JsonNumber) Json.createPointer("/id").getValue(example));
    Assertions.assertEquals(1, id.intValue());

    /**
     * Get a full object
     */
    JsonObject user = Json.createPointer("/user").getValue(example).asJsonObject();

    /**
     * Get an array
     */
    JsonArray userMentions = Json.createPointer("/user_mentions").getValue(example).asJsonArray();

    /**
     * Get an element from an array
     * Pointer do NOT finish with "/"
     */
    JsonObject mention = Json.createPointer("/user_mentions/0").getValue(example).asJsonObject();
    String mentionName = ((JsonString) Json.createPointer("/user_mentions/0/name").getValue(example)).getString();
    int mentionIndex0 = ((JsonNumber) Json.createPointer("/user_mentions/0/indices/1").getValue(example)).intValue();

    /**
     * Check if a structures contains a path
     */
    Assertions.assertTrue(Json.createPointer("/id").containsValue(example));
    Assertions.assertTrue(Json.createPointer("/user_mentions/0/indices/0").containsValue(example));
    Assertions.assertTrue(Json.createPointer("/user_mentions/0/indices/1").containsValue(example));
    Assertions.assertFalse(Json.createPointer("/user_mentions/0/indices/2").containsValue(example));

    /**
     * Add some value
     */
    JsonObject extendedExample = Json.createPointer("/timestamp").add(example, Json.createValue(System.currentTimeMillis()));
    extendedExample = Json.createPointer("/user_mentions/0/indices/2").add(extendedExample, Json.createValue(30));

    Assertions.assertTrue(Json.createPointer("/timestamp").containsValue(extendedExample));
    Assertions.assertEquals(30, ((JsonNumber) Json.createPointer("/user_mentions/0/indices/2").getValue(extendedExample)).intValue());

    //Replace
    example = Json.createPointer("/id").replace(example, Json.createValue(2));
    Assertions.assertEquals(2, example.getInt("id"));

    example = Json.createPointer("/user_mentions/0/indices/1").replace(example, Json.createValue(9999));
    Assertions.assertEquals(9999, ((JsonNumber) Json.createPointer("/user_mentions/0/indices/1").getValue(example)).intValue());

    //Remove
    example = Json.createPointer("/id").remove(example);
    Assertions.assertFalse(example.containsKey("id"));


  }


  @Test
  void patchDemo() {

    JsonObject example = Json.createReader(getClass().getClassLoader().getResourceAsStream("example.json")).readObject();

    /**
     * Get a value.
     * Pointer and patches must begin always with /
     * We still need to cast, since the only method fiven is getValue which returns JsonValue
     */
    JsonNumber id = ((JsonNumber) Json.createPointer("/id").getValue(example));
    Assertions.assertEquals(1, id.intValue());

    /**
     * Get a full object
     */
    JsonObject user = Json.createPointer("/user").getValue(example).asJsonObject();

    /**
     * Get an array
     */
    JsonArray userMentions = Json.createPointer("/user_mentions").getValue(example).asJsonArray();

    /**
     * Get an element from an array
     * Pointer do NOT finish with "/"
     */
    JsonObject mention = Json.createPointer("/user_mentions/0").getValue(example).asJsonObject();
    String mentionName = ((JsonString) Json.createPointer("/user_mentions/0/name").getValue(example)).getString();
    int mentionIndex0 = ((JsonNumber) Json.createPointer("/user_mentions/0/indices/1").getValue(example)).intValue();


  }

}
