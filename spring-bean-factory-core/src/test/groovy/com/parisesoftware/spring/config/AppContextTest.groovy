package com.parisesoftware.spring.config

import spock.lang.Specification

class AppContextTest extends Specification {

    def 'AppContext.instance: should return a non-null instance of a AppContext'() {

        when: 'AppContext.instance is invoked'
        AppContext resultant = AppContext.instance

        then: 'the resultant AppContext should not be null'
        resultant != null

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

    def 'AppContext.instance: should return the same instance of a AppContext'() {

        when: 'AppContext.instance is invoked multiple times'
        AppContext resultant1 = AppContext.instance
        AppContext resultant2 = AppContext.instance

        and: 'a BeanProvider is set on the first AppContext'
        resultant1.setBeanProvider(Mock(ISpringBeanProvider))

        then: 'both AppContexts should initially have a null ApplicationContext'
        old(resultant1?.beanProvider) == null
        old(resultant2?.beanProvider) == null

        and: 'the second AppContext should also have the set BeanProvider'
        resultant2.beanProvider == resultant1.beanProvider

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

    def 'AppContext.setBeanProvider(): should set the AppContext attribute'() {

        given: 'a mocked ISpringBeanProvider'
        ISpringBeanProvider mockAppContextAccessor = Mock(ISpringBeanProvider)

        when: 'AppContext.instance has it\'s ApplicationContext set'
        AppContext.instance.setBeanProvider(mockAppContextAccessor)

        then: 'the current AppContext value should be equal to our mocked instance'
        AppContext.instance.beanProvider == mockAppContextAccessor

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

    def 'getBean(): should call-through to underlying AppContext'() {

        given: 'a sample Bean Name'
        String testBeanName = 'Tiberious'

        and: 'a mocked AppContext'
        ISpringBeanProvider mockAppContextAccessor = Mock(ISpringBeanProvider)

        and: 'the AppContext instance has it\'s ApplicationContext set to our mocked AppContext'
        AppContext.instance.setBeanProvider(mockAppContextAccessor)

        when: 'the AppContext is queried to retrieve our sample Bean Name'
        AppContext.instance.getBean(testBeanName)

        then: 'the mocked AppContext\'s getBean() method should be invoked with the sample Bean Name'
        1 * mockAppContextAccessor.getBean(testBeanName)

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

}
