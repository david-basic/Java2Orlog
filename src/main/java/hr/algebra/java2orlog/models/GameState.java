package hr.algebra.java2orlog.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private List<SerializableDiceDetails> serializableDiceDetailsCollection;
    private List<SerializableMatchData> serializableMatchDataCollection;

    public GameState() {
        serializableDiceDetailsCollection = new ArrayList<>();
        serializableMatchDataCollection = new ArrayList<>();
    }

    public List<SerializableDiceDetails> getSerializableDiceDetailsCollection() {
        return serializableDiceDetailsCollection;
    }

    public void setSerializableDiceDetailsCollection(List<SerializableDiceDetails> serializableDiceDetailsCollection) {
        this.serializableDiceDetailsCollection = serializableDiceDetailsCollection;
    }

    public List<SerializableMatchData> getSerializableMatchDataCollection() {
        return serializableMatchDataCollection;
    }

    public void setSerializableMatchDataCollection(List<SerializableMatchData> serializableMatchDataCollection) {
        this.serializableMatchDataCollection = serializableMatchDataCollection;
    }
}
