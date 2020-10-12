/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagementsystem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Madhuransi
 */
@Entity
@Table(name = "timeslot", catalog = "timetablemanagementsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Timeslot_1.findAll", query = "SELECT t FROM Timeslot_1 t")
    , @NamedQuery(name = "Timeslot_1.findByTimeslotId", query = "SELECT t FROM Timeslot_1 t WHERE t.timeslotId = :timeslotId")
    , @NamedQuery(name = "Timeslot_1.findByTimetableID", query = "SELECT t FROM Timeslot_1 t WHERE t.timetableID = :timetableID")
    , @NamedQuery(name = "Timeslot_1.findByTimeslots", query = "SELECT t FROM Timeslot_1 t WHERE t.timeslots = :timeslots")})
public class Timeslot_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "timeslotId")
    private Integer timeslotId;
    @Basic(optional = false)
    @Column(name = "TimetableID")
    private int timetableID;
    @Basic(optional = false)
    @Column(name = "timeslots")
    private String timeslots;

    public Timeslot_1() {
    }

    public Timeslot_1(Integer timeslotId) {
        this.timeslotId = timeslotId;
    }

    public Timeslot_1(Integer timeslotId, int timetableID, String timeslots) {
        this.timeslotId = timeslotId;
        this.timetableID = timetableID;
        this.timeslots = timeslots;
    }

    public Integer getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(Integer timeslotId) {
        Integer oldTimeslotId = this.timeslotId;
        this.timeslotId = timeslotId;
        changeSupport.firePropertyChange("timeslotId", oldTimeslotId, timeslotId);
    }

    public int getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(int timetableID) {
        int oldTimetableID = this.timetableID;
        this.timetableID = timetableID;
        changeSupport.firePropertyChange("timetableID", oldTimetableID, timetableID);
    }

    public String getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(String timeslots) {
        String oldTimeslots = this.timeslots;
        this.timeslots = timeslots;
        changeSupport.firePropertyChange("timeslots", oldTimeslots, timeslots);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timeslotId != null ? timeslotId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timeslot_1)) {
            return false;
        }
        Timeslot_1 other = (Timeslot_1) object;
        if ((this.timeslotId == null && other.timeslotId != null) || (this.timeslotId != null && !this.timeslotId.equals(other.timeslotId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timetablemanagementsystem.Timeslot_1[ timeslotId=" + timeslotId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
