package com.digitalways.chillandfish.utils;

import java.util.List;
import java.util.Set;

public interface Converter <T>{
    List<T> setToList(Set<T> set);
}
