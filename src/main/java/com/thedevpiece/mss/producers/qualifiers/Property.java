package com.thedevpiece.mss.producers.qualifiers;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
    @Nonbinding String value();
}
