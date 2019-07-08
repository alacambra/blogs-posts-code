package lacambra.tech.dades;

import javax.json.*;

public class Dades {

  String namePointer = "/meta/view/columns/%i/name";
  String descriptionPointer = "/meta/view/columns/%i/description";
  JsonArray columns;

  public static void main(String[] args) {
    new Dades();
  }

  public Dades() {

    JsonObject json = Json.createReader(Dades.class.getClassLoader().getResourceAsStream("dev/indicadors-territorials-cat.json")).readObject();


    String colsPointer = "/meta/view/columns";
    String namePointer = "/meta/view/columns/%d/name";
    String descriptionPointer = "/meta/view/columns/%d/description";

    columns = Json.createPointer(colsPointer).getValue(json).asJsonArray();

    int total = Json.createPointer(colsPointer).getValue(json).asJsonArray().size();


    for (int i = 0; i < total; i++) {

      System.out.println(" -- " + i + " -- ");
      checkAndPrint(json, String.format(namePointer, i));
      checkAndPrint(json, String.format(descriptionPointer, i));

      System.out.println("--------------------------------------------");
    }
  }

  void checkAndPrint(JsonObject jsonObject, String pointerPath) {

    JsonPointer pointer = Json.createPointer(pointerPath);

    if (pointer.containsValue(jsonObject)) {
      System.out.println(((JsonString) pointer.getValue(jsonObject)).getString());
    }


  }


}
