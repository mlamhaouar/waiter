package com.nespresso.exercise.waiter.plate;

public class Plate {

    private String name;

    private static final String FOR = "for";
    private static final String REGEX_PLATE_FOR = ".* " + FOR + " [1-9]";

    public Plate(String name) {
        this.name = name;
    }

    public String print() {
        return this.name;
    }

    public Plate createSamePlat() {
        return new Plate(name);
    }

    public boolean isPlateFor() {
        return this.name.matches(REGEX_PLATE_FOR);
    }

    public int makeNumberPlateFor() {
        final int endPositionFor = this.name.lastIndexOf(FOR) + FOR.length();
        return Integer.valueOf(this.name.substring(endPositionFor, this.name.length()).trim());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Plate other = (Plate) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
