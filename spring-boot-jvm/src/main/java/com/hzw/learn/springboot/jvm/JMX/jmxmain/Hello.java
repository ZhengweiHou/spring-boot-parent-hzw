
public class Hello implements HelloMBean {
    public String name;
    public int index;

    public Hello(){
        this.name = "hello";
        this.index = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
