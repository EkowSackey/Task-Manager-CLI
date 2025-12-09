import models.*;
import services.Ui;
import services.UserService;
import utils.Autogen;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    UserService.seedUsers();
    UserService.init();


    //Ui.init();
}
