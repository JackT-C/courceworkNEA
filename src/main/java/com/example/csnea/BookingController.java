package com.example.csnea;
import java.sql.Date;
import java.sql.Time;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

public class BookingController {
    private final SimpleStringProperty name;
    private final SimpleStringProperty exerciseType;
    private final SimpleObjectProperty<Date> date;
    private final SimpleObjectProperty<Time> time;

    public BookingController(String name, String exerciseType, Date date, Time time) {
        this.name = new SimpleStringProperty(name);
        this.exerciseType = new SimpleStringProperty(exerciseType);
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleObjectProperty<>(time);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getExerciseType() {
        return exerciseType.get();
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType.set(exerciseType);
    }
    public Date getDate() {
        return date.get();
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public Time getTime() {
        return time.get();
    }

    public void setTime(Time time) {
        this.time.set(time);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty exerciseTypeProperty() {
        return exerciseType;
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public SimpleObjectProperty<Time> timeProperty() {
        return time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((exerciseType == null) ? 0 : exerciseType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookingController other = (BookingController) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (exerciseType == null) {
            if (other.exerciseType != null)
                return false;
        } else if (!exerciseType.equals(other.exerciseType))
            return false;
        return true;
    }
}


