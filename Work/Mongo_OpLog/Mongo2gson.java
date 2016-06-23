package com.entrib.mongo2gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.bson.types.ObjectId;

public final class Mongo2gson
{
  private static DateFormat defaultDateFormat = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss-SSS");
  private DateFormat dateFormat;
  
  public Mongo2gson()
  {
    this.dateFormat = defaultDateFormat;
  }
  
  public Mongo2gson(DateFormat dateFormat)
  {
    this.dateFormat = dateFormat;
  }
  
  public JsonArray getAsJsonArray(DBObject object)
  {
    if (!(object instanceof BasicDBList)) {
      throw new IllegalArgumentException("Expected BasicDBList as argument type!");
    }
    BasicDBList list = (BasicDBList)object;
    JsonArray jsonArray = new JsonArray();
    for (int i = 0; i < list.size(); i++)
    {
      Object dbObject = list.get(i);
      if ((dbObject instanceof BasicDBList)) {
        jsonArray.add(getAsJsonArray((BasicDBList)dbObject));
      } else if ((dbObject instanceof BasicDBObject)) {
        jsonArray.add(getAsJsonObject((BasicDBObject)dbObject));
      } else {
        jsonArray.add(getAsJsonPrimitive(dbObject));
      }
    }
    return jsonArray;
  }
  
  public JsonObject getAsJsonObject(DBObject object)
  {
    if (!(object instanceof BasicDBObject)) {
      throw new IllegalArgumentException("Expected BasicDBObject as argument type!");
    }
    BasicDBObject dbObject = (BasicDBObject)object;
    Set<String> keys = dbObject.keySet();
    Iterator<String> iterator = keys.iterator();
    JsonObject jsonObject = new JsonObject();
    while (iterator.hasNext())
    {
      String key = (String)iterator.next();
      Object innerObject = dbObject.get(key);
      if ((innerObject instanceof BasicDBList)) {
        jsonObject.add(key, getAsJsonArray((BasicDBList)innerObject));
      } else if ((innerObject instanceof BasicDBObject)) {
        jsonObject.add(key, getAsJsonObject((BasicDBObject)innerObject));
      } else {
        jsonObject.add(key, getAsJsonPrimitive(innerObject));
      }
    }
    return jsonObject;
  }
  
  public JsonElement getAsJsonPrimitive(Object value)
  {
    if ((value instanceof String)) {
      return new JsonPrimitive((String)value);
    }
    if ((value instanceof Character)) {
      return new JsonPrimitive((Character)value);
    }
    if ((value instanceof Integer)) {
      return new JsonPrimitive((Integer)value);
    }
    if ((value instanceof Long)) {
      return new JsonPrimitive((Long)value);
    }
    if ((value instanceof Double)) {
      return new JsonPrimitive((Double)value);
    }
    if ((value instanceof Boolean)) {
      return new JsonPrimitive((Boolean)value);
    }
    if ((value instanceof ObjectId)) {
      return new JsonPrimitive(value.toString());
    }
    if ((value instanceof Date)) {
      return new JsonPrimitive(this.dateFormat.format((Date)value));
    }
    throw new IllegalArgumentException("Unsupported value type for: " + value);
  }
}
