package com.example.viewfinder;

import java.security.Provider;

/**
 * @author xzj
 *         Created at 2017/4/26 17:29
 */
public interface Finder<T> {
    void inject(T host, Object source, Provider provider);
}
