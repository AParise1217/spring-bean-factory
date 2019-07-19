package com.parisesoftware.spring.factory.internal

import spock.lang.Specification

class NoImplementationExceptionTest extends Specification {

    def "NoImplementationException should be of type RuntimeException"() {

        when: 'a new NoImplementationException is instantiated'
        NoImplementationException testException = new NoImplementationException('blah')

        then: 'then the NoImplementationException instance is of type RuntimeException'
        testException instanceof RuntimeException
    }

}
