package sop_corba.ServidorCobrosPackage;


/**
* sop_corba/ServidorCobrosPackage/listInformeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* martes 26 de marzo de 2019 04:20:22 AM COT
*/

public final class listInformeHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.ClsInformeDTO value[] = null;

  public listInformeHolder ()
  {
  }

  public listInformeHolder (sop_corba.ClsInformeDTO[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.ServidorCobrosPackage.listInformeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.ServidorCobrosPackage.listInformeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.ServidorCobrosPackage.listInformeHelper.type ();
  }

}
