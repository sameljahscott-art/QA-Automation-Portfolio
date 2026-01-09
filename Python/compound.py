#input fields
principle = 0
rate = 0
time = 0

# Input principle
while True:
    principle = float(input("Enter the principle amount: "))
    if principle < 0:
        print("Principle can't be less than or equal to zero.")
    else:
        break

# Input rate
while True:
    rate = float(input("Enter the rate amount: "))
    if rate < 0:
        print("Rate can't be less than or equal to zero.")
    else:
        break

# Input time
while True:
    time = float(input("Enter the time amount: "))
    if time < 0:
        print("Time can't be less than or equal to zero.")
    else:
        break

# Calculate and display total
total = principle * pow((1 + rate / 100), time)
print(f"Balance after {time} year/s: ${total:.2f}")
#print(f"blance after  {time} year/s: ${total}")
