package sop_corba;

/**
* sop_corba/ClsPagoDTOHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* martes 26 de marzo de 2019 04:20:22 AM COT
*/

public final class ClsPagoDTOHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.ClsPagoDTO value = null;

  public ClsPagoDTOHolder ()
  {
  }

  public ClsPagoDTOHolder (sop_corba.ClsPagoDTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.ClsPagoDTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.ClsPagoDTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.ClsPagoDTOHelper.type ();
  }

}
