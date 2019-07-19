package com.parisesoftware.spring.factory.internal

import groovy.transform.PackageScope

/**
 * Bean Registry
 * <p>
 * Ledger for the BeanFactory to register and locate common objects and service Beans to be constructed.
 *
 * @version 1.0
 * @since 1.0
 */
@Singleton
class BeanRegistry {

    private Map<Object, String> beanRegistrationMap = [:]

    /**
     * Registers an Interface and Bean Name with the Registration Map.
     *  Note: if the type is already registered, this method does nothing
     * @version 1.0
     *
     * @param aType the Interface type to be queried against
     * @param aBeanName the name of the bean as defined in the AppContext
     *
     * @since 1.0
     */
    void register(Class aType, String aBeanName) {
        this.beanRegistrationMap.putIfAbsent(aType, aBeanName)
    }

    /**
     * Registers an Interface and Bean Name with the Registration Map.
     *  Note: if the type is already registered, this method does nothing
     * @version 1.0
     *
     * @param aGenericType the Generic type to be queried against
     * @param aType the Generic T value type to be queried against
     * @param aBeanName the name of the bean as defined in the AppContext
     *
     * @since 1.0
     */
    void register(Class aGenericType, Class aType, String aBeanName) {
        this.beanRegistrationMap.putIfAbsent(asTuple(aGenericType, aType), aBeanName)
    }

    /**
     * Fetches the Bean Name from the Registry matching the given Class Type
     * @version 1.0
     * @param aType the Class of the Bean
     * @return {@code String} the Bean Name
     * @since 1.0
     */
    String get(Class aType) {
        return this.beanRegistrationMap.get(aType)
    }

    /**
     * Fetches the Bean Name from the Registry matching the given Class Type
     * @version 1.0
     * @param aGenericType the Generic Class of the Bean
     * @param aType the Class of the Bean
     * @return {@code String} the Bean Name
     * @since 1.0
     */
    String get(Class aGenericType, Class aType) {
        return this.beanRegistrationMap.get(asTuple(aGenericType, aType))
    }

    /**
     * Checks if the given Class is registered
     * @version 1.0
     * @param aType the Class to check for
     * @return {@code boolean} true if the Class is registered, false if not
     * @since 1.0
     */
    boolean containsKey(Class aType) {
        return this.beanRegistrationMap.containsKey(aType)
    }

    /**
     * Checks if the given Class is registered
     * @version 1.0
     * @param aGenericType the Generic Class to check for
     * @param aType the Generic T value Class to check for
     * @return {@code boolean} true if the Class is registered, false if not
     * @since 1.0
     */
    boolean containsKey(Class aGenericType, Class aType) {
        return this.beanRegistrationMap.containsKey(asTuple(aGenericType, aType))
    }

    @PackageScope
    Tuple2 asTuple(Class aGenericClass, Class aTypedClass) {
        return new Tuple2(aGenericClass, aTypedClass)
    }

    /**
     * Set the Bean Registry to the param map.
     *  This is used in Unit test context for easier testing.
     * @version 1.0
     * @since 1.0
     */
    @PackageScope
    void setBeanRegistrationMap(Map aBeanRegistrationMap) {
        this.beanRegistrationMap = aBeanRegistrationMap
    }

}
