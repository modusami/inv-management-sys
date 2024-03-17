package dev.modusami.invmanagementsys.exceptions;

/**
 * A class of runtime exceptions thrown by methods to indicate that a price is invalid
 *
 * @author modusami
 * @version 4.0
 */
@SuppressWarnings("serial")
public class InvalidPriceException extends RuntimeException
{
    /**
     * default constructor for EmptyQueueException
     */
    public InvalidPriceException()
    {
        this(null);
    }

    /**
     * uses RuntimeException's constructor to construct a new empty queue
     * exception with a specified detail message
     *
     * @param message that explains the exception
     */
    public InvalidPriceException(String message)
    {
        super(message);
    }
}
