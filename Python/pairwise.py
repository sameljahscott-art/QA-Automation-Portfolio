#set the input fields
language_1 = ["eng","spanish","french"]
region_1 = ["us", "uk", "canada"]

#set the condition
language = (input("enter language"))
region = (input("enter region"))

# if statement
if language == language_1 and region == region_1:
    print("us")
elif language != "eng" and region == "us":
    print( "not ")    
elif language =="spanish" and region == "us":
    print( "us ")  
elif language == "eng" and region == "uk":
    print( "uk ")  
elif language == "french" and region == "canada":
    print( "canada ")  