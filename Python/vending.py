machinestate = input("Enter machine state(idle, has_money, item_selected)")
if machinestate == "idle":
  print ("No money")

elif machinestate == "has_money":
  print ("item is select")

elif machinestate == "item_selected":
  print ("item is dispensed")
else:
  print ("machine is empty")

 