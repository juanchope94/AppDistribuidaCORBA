package sop_corba;

/**
* sop_corba/ClsInformeDTOHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* martes 26 de marzo de 2019 04:20:22 AM COT
*/

public final class ClsInformeDTOHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.ClsInformeDTO value = null;

  public ClsInformeDTOHolder ()
  {
  }

  public ClsInformeDTOHolder (sop_corba.ClsInformeDTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.ClsInformeDTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.ClsInformeDTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.ClsInformeDTOHelper.type ();
  }

}