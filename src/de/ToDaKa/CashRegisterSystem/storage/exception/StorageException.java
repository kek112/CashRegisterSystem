package de.ToDaKa.CashRegisterSystem.storage.exception;

/**
 Class: StorageException
 @author Daniel Albrecht

 *
 */


public class StorageException extends Exception
{
    public StorageException( String message )
    {
        super( message );
    }

    public StorageException( Throwable cause )
    {
        super( cause );
    }

    public StorageException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
