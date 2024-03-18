package me.lunatic.lunaui.io;

import com.google.gson.internal.LinkedTreeMap;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MemoryConfiguration {

    protected final LinkedTreeMap<String, Object> values;

    public MemoryConfiguration() {
        this.values = new LinkedTreeMap<>();
    }

    public LinkedTreeMap<String, Object> getValues() {
        return this.values;
    }

    public boolean containsKey(String key) {
        return this.values.containsKey(key);
    }

    public void set(String key, Object value) {
        this.values.put(key, value);
    }

    public void setAndSave(String key, Object value) {
        this.set(key, value);
        this.save();
    }

    public String getString(String key, String def) {
        return (String) this.values.getOrDefault(key, def);
    }

    @Nullable
    public String getString(String key) {
        return this.getString(key, null);
    }

    public Integer getInt(String key, Integer def) {
        return (Integer) this.values.getOrDefault(key, def);
    }

    @Nullable
    public Integer getInt(String key) {
        return this.getInt(key, null);
    }

    public Double getDouble(String key, Double def) {
        return (Double) this.values.getOrDefault(key, def);
    }

    @Nullable
    public Double getDouble(String key) {
        return (Double) this.values.getOrDefault(key, null);
    }

    public Boolean getBoolean(String key, Boolean def) {
        return (Boolean) this.values.getOrDefault(key, def);
    }

    @Nullable
    public Boolean getBoolean(String key) {
        return this.getBoolean(key, null);
    }

    @SuppressWarnings("unchecked")
    public List<String> getStringList(String key, List<String> def) {
        return (List<String>) this.values.getOrDefault(key, def);
    }

    @Nullable
    public List<String> getStringList(String key) {
        return this.getStringList(key, null);
    }

    @SuppressWarnings("unchecked")
    public List<Integer> getIntList(String key, List<Integer> def) {
        return (List<Integer>) this.values.getOrDefault(key, def);
    }

    @Nullable
    public List<Integer> getIntList(String key) {
        return this.getIntList(key, null);
    }

    @SuppressWarnings("unchecked")
    public List<Double> getDoubleList(String key, List<Double> def) {
        return (List<Double>) this.values.getOrDefault(key, def);
    }

    @Nullable
    public List<Double> getDoubleList(String key) {
        return this.getDoubleList(key, null);
    }

    @SuppressWarnings("unchecked")
    public List<Boolean> getBooleanList(String key, List<Boolean> def) {
        return (List<Boolean>) this.values.getOrDefault(key, def);
    }

    @Nullable
    public List<Boolean> getBooleanList(String key) {
        return this.getBooleanList(key, null);
    }


    public void load() {
    }

    public void save() {
    }
}
