package com.parisesoftware.spring.factory

import com.parisesoftware.spring.config.AppContext
import com.parisesoftware.spring.factory.internal.BeanRegistry
import com.parisesoftware.spring.factory.internal.NoImplementationException
import spock.lang.Specification

class BeanFactoryTest extends Specification {

    def "newInstance(): should throw NoImplementationException if there is no interface of that type in the map"() {

        when: 'a type not registered with the BeanFactory is queried for'
        BeanFactory.newInstance(TestInterfaceBeanFactory)

        then: 'NoImplementationException is thrown'
        thrown(NoImplementationException)
    }

    def "newInstance(aType): should call out to AppContext for the param Bean Name, when it is in the map"() {

        given: 'a mocked AppContext'
        AppContext mockAppContext = Mock(AppContext)

        and: 'a global Mock for the AppContext\'s factory method to always return our mocked AppContext'
        GroovyMock(AppContext, global: true)
        AppContext.getInstance() >> mockAppContext

        and: 'a sample Type and Bean Name is registered with the BeanFactory'
        String testBeanName = 'Jack'
        BeanRegistry.instance.register(TestInterfaceBeanFactory, testBeanName)

        when: 'a type registered with the BeanFactory is queried for'
        BeanFactory.newInstance(TestInterfaceBeanFactory)

        then: 'our mocked AppContext is invoked with the sample Bean Name'
        1 * mockAppContext.getBean(testBeanName)
    }

    def "newInstance(aGenericType, aType): should call out to AppContext for the param Bean Name, when it is in the map"() {

        given: 'a mocked AppContext'
        AppContext mockAppContext = Mock(AppContext)

        and: 'a global Mock for the AppContext\'s factory method to always return our mocked AppContext'
        GroovyMock(AppContext, global: true)
        AppContext.getInstance() >> mockAppContext

        and: 'a sample Type and Bean Name is registered with the BeanFactory'
        String testBeanName = 'Jack'
        BeanRegistry.instance.register(TestGenericInterfaceBeanFactory, TestInterfaceBeanFactory, testBeanName)

        when: 'a type registered with the BeanFactory is queried for'
        BeanFactory.newInstance(TestGenericInterfaceBeanFactory, TestInterfaceBeanFactory)

        then: 'our mocked AppContext is invoked with the sample Bean Name'
        1 * mockAppContext.getBean(testBeanName)
    }

    private interface TestInterfaceBeanFactory extends TestGenericInterfaceBeanFactory {}

    private interface TestGenericInterfaceBeanFactory<T> {}

}
