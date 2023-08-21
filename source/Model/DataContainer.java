/* This file is part of Kings Key project
 * Copyright (C) 2023 Richard E. Varela (rvare) and Henry Kong.
 * 
 * Kings Key is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kings Key is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR PARTICULAR PURPOSE. See GNU General Public Licnese for more details.
 * 
 * You should have recieved a copy of the GNU General Public License along
 * with Kings Key. If not, see <https://www.gnu.org/licenses/>.
 */

package Model;

public class DataContainer {
    private String websiteName;
    private String accountName;
    private String userEmail;
    private String userPassword;

    public DataContainer() { } // Default constructor; does nothing

    public DataContainer(String[] data) {
        this.websiteName = data[0];
        this.accountName = data[1];
        this.userEmail = data[2];
        this.userPassword = data[3];
    }

    // Setters
    public String setWebsiteName(String websiteName) {
        return this.websiteName = websiteName;
    }

    public String setAccountName(String accountName) {
        return this.accountName = accountName;
    }

    public String setUserEmail(String userEmail) {
        return this.userEmail = userEmail;
    }

    public String setUserPassword(String userPassword) {
        return this.userPassword = userPassword;
    }

    // Getters
    public String getWebsiteName() {
        return this.websiteName;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
    
    public String toString() { // For testing purposes
        return String.format("%s;%s;%s;%s", websiteName, accountName, userEmail, userPassword);
    }

    public Object[] returnObjectRow() {
        Object[] objArr = new Object[4];
        objArr[0] = (Object)this.websiteName;
        objArr[1] = (Object)this.accountName;
        objArr[2] = (Object)this.userEmail;
        objArr[3] = (Object)this.userPassword;

        return objArr;
    }
} // End of DataContainer class