package com.parisesoftware.spring.factory.internal

/**
 * {@inheritDoc}
 *
 * Exception thrown when there is no Implementation for the Requested Interface
 *
 * @since 1.0
 * @version 1.0
 */
class NoImplementationException extends RuntimeException {

    NoImplementationException(String message) {
        super(message)
    }

}
