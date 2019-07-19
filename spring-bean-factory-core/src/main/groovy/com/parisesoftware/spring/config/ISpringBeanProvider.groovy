package com.parisesoftware.spring.config

/**
 * <h1>Spring Bean Provider</h1>
 * <p>Abstraction for a class that Creates Spring Beans</p>
 *
 * @since   1.0
 * @version 1.0
 */
interface ISpringBeanProvider {

    /**
     * Returns the Object instance associated with the given Spring Bean Name
     * @param beanName the Spring Bean Name
     * @return {@code Object} generic boxing of the bean instance
     */
    Object getBean(String beanName)

}
