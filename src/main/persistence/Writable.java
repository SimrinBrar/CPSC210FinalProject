package persistence;

import org.json.JSONObject;

//Citation: code obtained from JsonSerializationDemo
//          URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
