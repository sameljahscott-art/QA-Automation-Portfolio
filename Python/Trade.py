trade = input("stream to plaftorm(pending,clear_BK, clear_CH)")
if trade == "pending":
    print("wait to be clear") 
elif trade =="clear_BK": 
    print("to to PTMS")  
elif trade =="clear_CH":
    print("yes to to PTMS")   
elif trade =="":
    print("no trade")     