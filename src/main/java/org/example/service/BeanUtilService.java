package org.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class BeanUtilService {
    public void copyProperties(Object source, Object target) throws BeansException {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames(Object source) {
        BeanWrapperImpl src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName();
            Object pv = src.getPropertyValue(name);
            if (pv == null) {
                emptyNames.add(name);
            }
        }
        return Arrays.stream(pds).map(FeatureDescriptor::getName).filter(name -> src.getPropertyValue(name) == null).toArray(String[]::new);
    }
}
