package model;

/**
 *
 * @author thiago
 */
public class Clientes extends Pessoa{
    
    private String email;
    private int status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }    
}
