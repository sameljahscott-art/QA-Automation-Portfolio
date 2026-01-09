#Set the correct credentials
correct_username = "admin"
correct_password = "1234"
# set condition
user_name = input("Enter username") 
pass_word = input("Enter password")
#write the statment
if user_name == correct_username and pass_word == correct_password:
    print("login succesfull.")
elif user_name == correct_username and pass_word != correct_password:
    print("show error message: this is not correct")  
elif user_name != correct_username and pass_word == correct_password:
    print("Error: Incorrect username.")
elif user_name != correct_username and pass_word !=correct_password:
    print("Error: Incorrect username and password.")   