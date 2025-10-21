package pojos;

import java.util.List;

public class GoRestUsersPojo{
    private List<DataItem> data;
    private Meta meta;

    public void setData(List<DataItem> data){
        this.data = data;
    }

    public List<DataItem> getData(){
        return data;
    }

    public void setMeta(Meta meta){
        this.meta = meta;
    }

    public Meta getMeta(){
        return meta;
    }

    @Override
    public String toString(){
        return
                "GoRestUsersPojo{" +
                        "data = '" + data + '\'' +
                        ",meta = '" + meta + '\'' +
                        "}";
    }
}