# action code converter

f = open("betteractions.txt", "r")

strout = ""

for line in f:
    bruh = line.split() 
    if bruh[1] == "0":
        strout += "Cursor"
    elif bruh[1] == "1":
        strout += "Grandma"
    elif bruh[1] == "2":
        strout += "Farm"
    elif bruh[1] == "3":
        strout += "Mine"
    elif bruh[1] == "4":
        strout += "Factory"

    if bruh[0] == "1":
        strout += "_up"
    
    if bruh[0] == "3":
        strout += "wait"
    
    strout += "\n"

print(strout)

