public class Controller {
    // May contain certain global constants needed.
    // Have constant that represents data file directory/path
    public static void main(Stirng[] args) {
        /*
            Declare backend as Backend
            Declare applicationUI as UI
            TRY // Will be used to handle errors and handle them appropriately
                Call Backend.createInstance() assign to backend
                Pass file path constant to backend
                    Will decrypt data file and intialize backend
                Initialize applicationUI and pass backend to it
                WHILE status == active
                    // Might use a switch statement instead with special constants
                    // Usually faster to use switch statement
                    IF user adds new record, signal from UI
                        Pass new data to backend
                    ELSE IF user selects to change record, signal from UI
                        Update data in backend
                        Update applicationUI
                    ELSE IF user deletes a record, signal from UI
                        Get new password from application UI
                        Update data in backend with new password
                        Update applicationUI
                    ELSE IF user changes theme, signal from UI
                        Update applicationUI with appropriate arguments
                    ELSE IF user generates new password for record, signal from UI
                        Get new password from applicationUI
                        Update data in backend with new password
                        Update applicationUI
                    END IF-ELSE
                END WHILE
            CATCH
                Error handling
            END TRY-CATCH
            Clean up operations happen here when user is done
        */
    }
}

/*
NOTES
=====

- There should be some form of data validation for some of the operations
- Even though the psuedo code uses an if statement, we could use switch instead
    - Switch statements are faster
        - Doesn't need to check each if-else
    - Can make unique values that represent signals
- Will need to research on the different exceptions to handle them appropriately
    - You can have more than one catch, which will make it easy to handle certain errors
- 
*/