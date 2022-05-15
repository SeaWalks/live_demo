package model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "trims")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "model_id", "trimName" }) })
public class Trim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trim_id")
    private int trimId;

    @Column(nullable = false)
    private String trimName;

    @Column(nullable = false)
    private int cost;

    //Unidirectional Many-To-Many from Trim->Feature w/ TrimFeature as a junction; 
    //One Trim has many Features; Parent knows child, child doesn't know parents.
    @OneToMany(mappedBy = "trim")
    private Set<TrimFeature> trimFeatures;

    ///Bi-directional One-to-Many between Trim->Model
    //One Model has many Trims; parent and children know each other.
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    public Trim() {
    }

    public Trim(String trimName, int cost, Model model) {
        this.trimName = trimName;
        this.cost = cost;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Trim: " + trimName + " (ID " + trimId + ")";
    }

    public int getTrimId() {
        return trimId;
    }

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Set<TrimFeature> getTrimFeatures() {
        return trimFeatures;
    }

    public void setTrimFeatures(Set<TrimFeature> trimFeatures) {
        this.trimFeatures = trimFeatures;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
