package com.parisesoftware.spring.factory.internal

import spock.lang.Specification

class BeanRegistryTest extends Specification {

    def "register(aType): should call through to the underlying beanRegistrationMap"() {

        given: 'some sample data'
        String testBeanName = 'baked'
        Class testClass = ClassToBeRegistered

        and: 'a mocked Map'
        Map mockedMap = Mock(Map)

        and: 'the mocked Map is set on the BeanRegistry'
        BeanRegistry.instance.setBeanRegistrationMap(mockedMap)

        when: 'the BeanRegistry is queried to register with our sample data'
        BeanRegistry.instance.register(testClass, testBeanName)

        then: 'the Mocked Map was invoked with the correct parameters'
        1 * mockedMap.putIfAbsent(testClass, testBeanName)

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

    def "register(aGenericType, aType): should call through to the underlying beanRegistrationMap"() {

        given: 'some sample data'
        String testBeanName = 'baked'
        Class testClass = ClassToBeRegistered
        Class testGenericClass = GenericClassToBeRegistered

        and: 'a mocked Map'
        Map mockedMap = Mock(Map)

        and: 'the mocked Map is set on the BeanRegistry'
        BeanRegistry.instance.setBeanRegistrationMap(mockedMap)

        when: 'the BeanRegistry is queried to register with our sample data'
        BeanRegistry.instance.register(testGenericClass, testClass, testBeanName)

        then: 'the Mocked Map was invoked with the correct parameters'
        1 * mockedMap.putIfAbsent(new Tuple2(testGenericClass, testClass), testBeanName)

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

    def "get(aType): should call through to the underlying beanRegistrationMap"() {

        given: 'some sample data'
        Class testClass = ClassToBeRegistered

        and: 'a mocked Map'
        Map mockedMap = Mock(Map)

        and: 'the mocked Map is set on the BeanRegistry'
        BeanRegistry.instance.setBeanRegistrationMap(mockedMap)

        when: 'the BeanRegistry is queried to get the bean name our sample Class'
        BeanRegistry.instance.get(testClass)

        then: 'the Mocked Map was invoked with the correct parameters'
        1 * mockedMap.get(testClass)

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

    def "get(aGenericType, aType): should call through to the underlying beanRegistrationMap"() {

        given: 'some sample data'
        Class testClass = ClassToBeRegistered
        Class testGenericClass = GenericClassToBeRegistered

        and: 'a mocked Map'
        Map mockedMap = Mock(Map)

        and: 'the mocked Map is set on the BeanRegistry'
        BeanRegistry.instance.setBeanRegistrationMap(mockedMap)

        when: 'the BeanRegistry is queried to get the bean name our sample Class'
        BeanRegistry.instance.get(testGenericClass, testClass)

        then: 'the Mocked Map was invoked with the correct parameters'
        1 * mockedMap.get(new Tuple2(testGenericClass, testClass))

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

    def "containsKey(aType): should call through to the underlying beanRegistrationMap"() {

        given: 'some sample data'
        Class testClass = ClassToBeRegistered

        and: 'a mocked Map'
        Map mockedMap = Mock(Map)

        and: 'the mocked Map is set on the BeanRegistry'
        BeanRegistry.instance.setBeanRegistrationMap(mockedMap)

        when: 'the BeanRegistry is queried for if our sample Class is registered'
        BeanRegistry.instance.containsKey(testClass)

        then: 'the Mocked Map was invoked with the correct parameters'
        1 * mockedMap.containsKey(testClass)

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

    def "containsKey(aGenericType, aType): should call through to the underlying beanRegistrationMap"() {

        given: 'some sample data'
        Class testClass = ClassToBeRegistered
        Class testGenericClass = GenericClassToBeRegistered

        and: 'a mocked Map'
        Map mockedMap = Mock(Map)

        and: 'the mocked Map is set on the BeanRegistry'
        BeanRegistry.instance.setBeanRegistrationMap(mockedMap)

        when: 'the BeanRegistry is queried for if our sample Class is registered'
        BeanRegistry.instance.containsKey(testGenericClass, testClass)

        then: 'the Mocked Map was invoked with the correct parameters'
        1 * mockedMap.containsKey(new Tuple2(testGenericClass, testClass))

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

}

/**
 * Test Class for use in the BeanRegistry's Unit Test Execution
 */
interface GenericClassToBeRegistered<T> {}

/**
 * Test Class for use in the BeanRegistry's Unit Test Execution
 */
class ClassToBeRegistered implements GenericClassToBeRegistered {}
