package com.bknife.base.util.tuple;

public class Tuple4<V1, V2, V3, V4> {
    private V1 value1;
    private V2 value2;
    private V3 value3;
    private V4 value4;

    public Tuple4(V1 value1, V2 value2, V3 value3, V4 value4) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    public Tuple4() {
    }

    public V1 getValue1() {
        return value1;
    }

    public void setValue1(V1 value1) {
        this.value1 = value1;
    }

    public V2 getValue2() {
        return value2;
    }

    public void setValue2(V2 value2) {
        this.value2 = value2;
    }

    public V3 getValue3() {
        return value3;
    }

    public void setValue3(V3 value3) {
        this.value3 = value3;
    }

    public V4 getValue4() {
        return value4;
    }

    public void setValue4(V4 value4) {
        this.value4 = value4;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
        result = prime * result + ((value2 == null) ? 0 : value2.hashCode());
        result = prime * result + ((value3 == null) ? 0 : value3.hashCode());
        result = prime * result + ((value4 == null) ? 0 : value4.hashCode());
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
        Tuple4<?, ?, ?, ?> other = (Tuple4<?, ?, ?, ?>) obj;
        if (value1 == null) {
            if (other.value1 != null)
                return false;
        } else if (!value1.equals(other.value1))
            return false;
        if (value2 == null) {
            if (other.value2 != null)
                return false;
        } else if (!value2.equals(other.value2))
            return false;
        if (value3 == null) {
            if (other.value3 != null)
                return false;
        } else if (!value3.equals(other.value3))
            return false;
        if (value4 == null) {
            if (other.value4 != null)
                return false;
        } else if (!value4.equals(other.value4))
            return false;
        return true;
    }
}
