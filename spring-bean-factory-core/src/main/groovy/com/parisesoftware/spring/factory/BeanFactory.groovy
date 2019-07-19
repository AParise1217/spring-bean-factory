package com.parisesoftware.spring.factory

import com.parisesoftware.spring.config.AppContext
import com.parisesoftware.spring.factory.internal.BeanRegistry
import com.parisesoftware.spring.factory.internal.NoImplementationException

/**
 * Bean Factory
 * <p>
 * a Simple Factory to encapsulate the fetching of Spring Beans
 *
 * @version 1.0
 * @since 1.0
 */
class BeanFactory {

    private BeanFactory() {}

    /**
     * Returns a new Instance of the Bean associated with the given Interface Type.
     *  Note: this does not check if the Bean actually implements the Interface.
     * @version 1.0
     *
     * @param aType the Class of the Bean to retrieve
     * @return {@code Object} the Constructed Bean
     *
     * @throws {@code NoImplementationException} when there is no registered bean for the interface of param type
     * @see NoImplementationException
     * @since 1.0
     */
    static Object newInstance(Class aType) throws NoImplementationException {
        if (!BeanRegistry.instance.containsKey(aType)) {
            throw new NoImplementationException("No implementation registered for interface `${aType}`.")
        }

        String beanName = BeanRegistry.instance.get(aType)
        return AppContext.instance.getBean(beanName)
    }

    /**
     * Returns a new Instance of the Bean associated with the given Interface Type.
     *  Note: this does not check if the Bean actually implements the Interface.
     * @version 1.0
     *
     * @param aGenericType the Generic Class of the Bean to retrieve
     * @param aType the Class of the Bean to retrieve
     * @return {@code Object} the Constructed Bean
     *
     * @throws {@code NoImplementationException} when there is no registered bean for the interface of param type
     * @see NoImplementationException
     * @since 1.0
     */
    static Object newInstance(Class aGenericType, Class aType) throws NoImplementationException {
        if (!BeanRegistry.instance.containsKey(aGenericType, aType)) {
            throw new NoImplementationException("No implementation registered for generic interface `${aGenericType}`" +
                    ", with a T value of `${aType}`.")
        }

        String beanName = BeanRegistry.instance.get(aGenericType, aType)
        return AppContext.instance.getBean(beanName)
    }

}
