package sop_corba;


/**
* sop_corba/ClsPagoDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* martes 26 de marzo de 2019 04:20:22 AM COT
*/

public final class ClsPagoDTO implements org.omg.CORBA.portable.IDLEntity
{
  public int id = (int)0;
  public String placa = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNoTicket() {
        return noTicket;
    }

    public void setNoTicket(String noTicket) {
        this.noTicket = noTicket;
    }
  public String noTicket = null;

  public ClsPagoDTO ()
  {
  } // ctor

  public ClsPagoDTO (int _id, String _placa, String _noTicket)
  {
    id = _id;
    placa = _placa;
    noTicket = _noTicket;
  } // ctor

} // class ClsPagoDTO
