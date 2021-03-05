public class HealthzAPIPojo {

    public String status;
    public String code;
    public String version;

    public HealthzAPIPojo(){

    }

//    public HealthzAPIPojo(String status, String code, String version){
//        this.status = status;
//        this.code = code;
//        this.version = version;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
