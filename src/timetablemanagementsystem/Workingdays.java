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
@Table(name = "workingdays", catalog = "timetablemanagementsystem", schema = "")
@NamedQueries({
    @NamedQuery(name = "Workingdays.findAll", query = "SELECT w FROM Workingdays w")
    , @NamedQuery(name = "Workingdays.findByTimetableID", query = "SELECT w FROM Workingdays w WHERE w.timetableID = :timetableID")
    , @NamedQuery(name = "Workingdays.findByUserType", query = "SELECT w FROM Workingdays w WHERE w.userType = :userType")
    , @NamedQuery(name = "Workingdays.findByNoOfWorkingDays", query = "SELECT w FROM Workingdays w WHERE w.noOfWorkingDays = :noOfWorkingDays")
    , @NamedQuery(name = "Workingdays.findByWDMonday", query = "SELECT w FROM Workingdays w WHERE w.wDMonday = :wDMonday")
    , @NamedQuery(name = "Workingdays.findByWDTuesday", query = "SELECT w FROM Workingdays w WHERE w.wDTuesday = :wDTuesday")
    , @NamedQuery(name = "Workingdays.findByWDWednesday", query = "SELECT w FROM Workingdays w WHERE w.wDWednesday = :wDWednesday")
    , @NamedQuery(name = "Workingdays.findByWDThursday", query = "SELECT w FROM Workingdays w WHERE w.wDThursday = :wDThursday")
    , @NamedQuery(name = "Workingdays.findByWDFriday", query = "SELECT w FROM Workingdays w WHERE w.wDFriday = :wDFriday")
    , @NamedQuery(name = "Workingdays.findByWDSaturday", query = "SELECT w FROM Workingdays w WHERE w.wDSaturday = :wDSaturday")
    , @NamedQuery(name = "Workingdays.findByWDSunday", query = "SELECT w FROM Workingdays w WHERE w.wDSunday = :wDSunday")
    , @NamedQuery(name = "Workingdays.findByWorkingHours", query = "SELECT w FROM Workingdays w WHERE w.workingHours = :workingHours")})
public class Workingdays implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TimetableID")
    private Integer timetableID;
    @Basic(optional = false)
    @Column(name = "UserType")
    private String userType;
    @Basic(optional = false)
    @Column(name = "NoOfWorkingDays")
    private int noOfWorkingDays;
    @Basic(optional = false)
    @Column(name = "WDMonday")
    private boolean wDMonday;
    @Basic(optional = false)
    @Column(name = "WDTuesday")
    private boolean wDTuesday;
    @Basic(optional = false)
    @Column(name = "WDWednesday")
    private boolean wDWednesday;
    @Basic(optional = false)
    @Column(name = "WDThursday")
    private boolean wDThursday;
    @Basic(optional = false)
    @Column(name = "WDFriday")
    private boolean wDFriday;
    @Basic(optional = false)
    @Column(name = "WDSaturday")
    private boolean wDSaturday;
    @Basic(optional = false)
    @Column(name = "WDSunday")
    private boolean wDSunday;
    @Basic(optional = false)
    @Column(name = "WorkingHours")
    private String workingHours;

    public Workingdays() {
    }

    public Workingdays(Integer timetableID) {
        this.timetableID = timetableID;
    }

    public Workingdays(Integer timetableID, String userType, int noOfWorkingDays, boolean wDMonday, boolean wDTuesday, boolean wDWednesday, boolean wDThursday, boolean wDFriday, boolean wDSaturday, boolean wDSunday, String workingHours) {
        this.timetableID = timetableID;
        this.userType = userType;
        this.noOfWorkingDays = noOfWorkingDays;
        this.wDMonday = wDMonday;
        this.wDTuesday = wDTuesday;
        this.wDWednesday = wDWednesday;
        this.wDThursday = wDThursday;
        this.wDFriday = wDFriday;
        this.wDSaturday = wDSaturday;
        this.wDSunday = wDSunday;
        this.workingHours = workingHours;
    }

    public Integer getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(Integer timetableID) {
        Integer oldTimetableID = this.timetableID;
        this.timetableID = timetableID;
        changeSupport.firePropertyChange("timetableID", oldTimetableID, timetableID);
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        String oldUserType = this.userType;
        this.userType = userType;
        changeSupport.firePropertyChange("userType", oldUserType, userType);
    }

    public int getNoOfWorkingDays() {
        return noOfWorkingDays;
    }

    public void setNoOfWorkingDays(int noOfWorkingDays) {
        int oldNoOfWorkingDays = this.noOfWorkingDays;
        this.noOfWorkingDays = noOfWorkingDays;
        changeSupport.firePropertyChange("noOfWorkingDays", oldNoOfWorkingDays, noOfWorkingDays);
    }

    public boolean getWDMonday() {
        return wDMonday;
    }

    public void setWDMonday(boolean wDMonday) {
        boolean oldWDMonday = this.wDMonday;
        this.wDMonday = wDMonday;
        changeSupport.firePropertyChange("WDMonday", oldWDMonday, wDMonday);
    }

    public boolean getWDTuesday() {
        return wDTuesday;
    }

    public void setWDTuesday(boolean wDTuesday) {
        boolean oldWDTuesday = this.wDTuesday;
        this.wDTuesday = wDTuesday;
        changeSupport.firePropertyChange("WDTuesday", oldWDTuesday, wDTuesday);
    }

    public boolean getWDWednesday() {
        return wDWednesday;
    }

    public void setWDWednesday(boolean wDWednesday) {
        boolean oldWDWednesday = this.wDWednesday;
        this.wDWednesday = wDWednesday;
        changeSupport.firePropertyChange("WDWednesday", oldWDWednesday, wDWednesday);
    }

    public boolean getWDThursday() {
        return wDThursday;
    }

    public void setWDThursday(boolean wDThursday) {
        boolean oldWDThursday = this.wDThursday;
        this.wDThursday = wDThursday;
        changeSupport.firePropertyChange("WDThursday", oldWDThursday, wDThursday);
    }

    public boolean getWDFriday() {
        return wDFriday;
    }

    public void setWDFriday(boolean wDFriday) {
        boolean oldWDFriday = this.wDFriday;
        this.wDFriday = wDFriday;
        changeSupport.firePropertyChange("WDFriday", oldWDFriday, wDFriday);
    }

    public boolean getWDSaturday() {
        return wDSaturday;
    }

    public void setWDSaturday(boolean wDSaturday) {
        boolean oldWDSaturday = this.wDSaturday;
        this.wDSaturday = wDSaturday;
        changeSupport.firePropertyChange("WDSaturday", oldWDSaturday, wDSaturday);
    }

    public boolean getWDSunday() {
        return wDSunday;
    }

    public void setWDSunday(boolean wDSunday) {
        boolean oldWDSunday = this.wDSunday;
        this.wDSunday = wDSunday;
        changeSupport.firePropertyChange("WDSunday", oldWDSunday, wDSunday);
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        String oldWorkingHours = this.workingHours;
        this.workingHours = workingHours;
        changeSupport.firePropertyChange("workingHours", oldWorkingHours, workingHours);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timetableID != null ? timetableID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workingdays)) {
            return false;
        }
        Workingdays other = (Workingdays) object;
        if ((this.timetableID == null && other.timetableID != null) || (this.timetableID != null && !this.timetableID.equals(other.timetableID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timetablemanagementsystem.Workingdays[ timetableID=" + timetableID + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
