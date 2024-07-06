package pmf.android.sportscoresbysbm2.data.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite_teams")
public class TeamEntity {

    @PrimaryKey
    public Long id;
    public String name;

    @Ignore
    public TeamEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeamEntity () {
    }



    public Long getTeamId() {
        return this.id;
    }

    public String getTeamName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
