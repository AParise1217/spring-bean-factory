package com.parisesoftware.spring.config

import groovy.transform.PackageScope

/**
 * <h1>App Context</h1>
 * <p>a Thread-Safe Singleton to a {@link ISpringBeanProvider}</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Singleton
class AppContext {

    /**
     * The Wrapped ApplicationContext
     */
    @PackageScope
    ISpringBeanProvider beanProvider

    /**
     * Mutator for the Wrapped ApplicationContextAccessor instance
     * @param applicationContextAccessor the appContext instance to be wrapped
     */
    void setBeanProvider(ISpringBeanProvider beanProvider) {
        this.beanProvider = beanProvider
    }

    /**
     * Returns the Object instance associated with the given Spring Bean Name
     * @param beanName the Spring Bean Name
     * @return {@code Object} generic box for the bean instance
     */
    Object getBean(String beanName) {
        return this.beanProvider?.getBean(beanName)
    }

}
