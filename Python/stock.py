price= int(input("Enter the price of the stock??: "))


if 2000 <= price <= 3000:
    print ("Stock is in overbought zone")
elif price <= 1000 :
    print ("Stock is in underbought zone")
elif price >= 1500:
    print ("add to watchlist.")
else:
    print("Don't add in watchlist.")