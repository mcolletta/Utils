package com.xenoage.utils.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for methods that are guaranteed to not return null
 * and for fields and variables that may not contain null.
 * 
 * Can be used to mark methods and fields, where it is unclear if
 * null is allowed or not.
 * 
 * @deprecated Use {@link NonNull}. Renamed for compatibility with
 * other standards.
 *
 * @author Andreas Wenger
 */
@Retention(RetentionPolicy.CLASS)
@Deprecated
public @interface NeverNull {
}
