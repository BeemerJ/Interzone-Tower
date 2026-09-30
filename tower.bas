' ============================================
' INTERZØNE TØWER
' Original Java game ported to FreeBASIC
' ============================================

Randomize Timer

' --------------------------------------------
' FUNCTIONS
' --------------------------------------------

Function RandomRange(minValue As Integer, maxExclusive As Integer) As Integer
    Return Int(Rnd * (maxExclusive - minValue)) + minValue
End Function

Sub WaitSeconds(seconds As Integer)
    Sleep seconds * 1000
End Sub


' --------------------------------------------
' PLAYER NAME
' --------------------------------------------

Dim As String name

Print "Please enter a Name:"
Input name
name = UCase(name)

' --------------------------------------------
' TIME INIT
' --------------------------------------------

Dim As Integer timeSlow = 2
Dim As Integer timeCinematic = timeSlow * 2

WaitSeconds(timeSlow)

' --------------------------------------------
' TITLE SPLASH
' --------------------------------------------

Print "-----------------------------------"
Print "I N T E R Z Ø  N E    T Ø W E R"
Print "-----------------------------------"

Print "You find a tall tower..."
Print "-----------------------------------"

Dim As Integer nFloor = 1

WaitSeconds(timeCinematic)

' --------------------------------------------
' INIT ROLLS
' --------------------------------------------

Dim As Integer damMin = RandomRange(40, 60)
Dim As Integer damMax = 100
Dim As Integer damBound = 50
Dim As Integer damBoundMin = 5

' --------------------------------------------
' CHEATS
' --------------------------------------------

If name = "DANTE" Then

    damMin = 99
    damMax = 150

    Print "You whip out Ebony and Ivory..."

    WaitSeconds(timeSlow)

    timeSlow = timeSlow \ 2

ElseIf name = "TONBERRY" Then

    timeSlow = timeSlow * 2
    damMax = damMax * 2
    damMin = damMin * 2

    Print "You smell a bit fishy..."

    WaitSeconds(timeSlow)

End If

Dim As Boolean playerAlive = True

' --------------------------------------------
' ENEMY TABLE
' --------------------------------------------

Dim As String enemy(0 To 5)

enemy(0) = "Snake"
enemy(1) = "Spider"
enemy(2) = "Scorpion"
enemy(3) = "Salamander"
enemy(4) = "Slug"
enemy(5) = "Scarab"

' --------------------------------------------
' MAIN TOWER LOOP
' --------------------------------------------

For i As Integer = 0 To 8

    Print "You enter Floor Number " & nFloor & "..."

    WaitSeconds(timeCinematic)

    ' ----------------------------------------
    ' ENEMY
    ' ----------------------------------------

    Dim As Integer nEnemy = RandomRange(0, 6)

    ' ----------------------------------------
    ' EVOLVED ROLL
    ' ----------------------------------------

    Dim As Boolean isEvolved = False
    isEvolved = (RandomRange(0, 2) = 1)

    Dim As String enemyEvolved = " "
    Dim As Integer evolveRoll = RandomRange(0, 8)

    If evolveRoll > 1 Then

        enemyEvolved = " "

    Else

        enemyEvolved = " EVOLVED "
        damBoundMin = damBoundMin + (nFloor * 15)

    End If

    ' ----------------------------------------
    ' POTION
    ' ----------------------------------------

    Dim As Integer potionRoll = RandomRange(0, 12)
    Dim As Integer potionBuff

    potionBuff = RandomRange(4, 8) * RandomRange(2, 8)

    If potionRoll >= 8 Then

        damMin = damMin + potionBuff
        damMax = damMax + potionBuff

        Dim As Double buffPercent
        buffPercent = (potionBuff / damMax) * 100

        Print "You found a Potion!"
        Print "-----------------------------------"
        Print "You feel a " & Int(buffPercent) & "% boost in strength..."
        Print "-----------------------------------"

        WaitSeconds(timeSlow)

    End If

    ' ----------------------------------------
    ' ENCOUNTER
    ' ----------------------------------------

    Print "You encounter an" & enemyEvolved & "enemy " & enemy(nEnemy) & ", you attack it..."

    WaitSeconds(timeCinematic)

    ' ----------------------------------------
    ' CRIT MISS
    ' ----------------------------------------

    Dim As Integer nCritmiss = RandomRange(0, 5)
    Dim As Integer nFallDam = nFloor * RandomRange(8, 12)

    If nCritmiss = 1 Then

        damMax = damMax - nFallDam

        Print "You stumble and hurt yourself for " & nFallDam & "HP..."

        WaitSeconds(timeSlow)

    End If

    ' ----------------------------------------
    ' PLAYER DAMAGE
    ' ----------------------------------------

    Dim As Integer nDamage = RandomRange(damMin, damMax)

    If nDamage >= (nDamage * 0.33) Then

        Print "You deal " & nDamage & " damage to the " & enemy(nEnemy) & "..."

        WaitSeconds(timeCinematic)

    Else

        Print "You do " & nDamage & " damage to the " & enemy(nEnemy) & ", you fear for your life..."

        WaitSeconds(timeCinematic)

    End If

    ' ----------------------------------------
    ' CRITICAL
    ' ----------------------------------------

    If nDamage >= (damMax * 0.8) Then

        Print "A CRITICAL HIT..."

        WaitSeconds(timeSlow)

    ElseIf nDamage <= (damMax * 0.33) Then

        Print "You barely hit the " & enemy(nEnemy) & "..."

        WaitSeconds(timeSlow)

    End If

    ' ----------------------------------------
    ' ENEMY HEALTH
    ' ----------------------------------------

    Dim As Integer nDamageRoll = RandomRange(damBoundMin, damBound)

    WaitSeconds(timeSlow)

    If nDamage <= (damBound * 0.75) Then

        Print "You sense a bad omen..."

        WaitSeconds(timeSlow)

    End If

    ' ----------------------------------------
    ' LEVEL UP / DEATH
    ' ----------------------------------------

    Dim As Double enemyScale = RandomRange(0, 8)

    If nDamage > nDamageRoll Then

        Print "You have slayed the " & enemy(nEnemy) & ", Well done!"

        nFloor = nFloor + 1
        damMin = damMin + 25
        damMax = damMax + 50

        damBound = damBound + 50 + ((enemyScale * nFloor) / 2)

        If isEvolved Then

            damBoundMin = (damBoundMin + 30) - (nFloor * 20)

        Else

            damBoundMin = damBoundMin + 30

        End If

        WaitSeconds(timeSlow)

        ' ------------------------------------
        ' FEELING
        ' ------------------------------------

        If enemyScale >= 4 And enemyScale < 6 Then

            Print "You get an uneasy feeling..."

            WaitSeconds(timeSlow)

        ElseIf enemyScale > 6 Then

            Print "You get a terrible feeling..."

            WaitSeconds(timeSlow)

        End If

        Print "-----------------------------------"

        WaitSeconds(timeSlow)

    Else

        ' ------------------------------------
        ' GAME OVER
        ' ------------------------------------

        Print "-----------------------------------"
        Print "You have fumbled your swing on Floor " & nFloor & " and got eaten by a " & enemy(nEnemy) & ", RIP " & name & "..."
        Print "-----------------------------------"
        Print "☠   ☠   ☠   ☠   ☠   ☠   ☠   ☠"
        Print "-----------------------------------"

        WaitSeconds(timeSlow)

        Print "Your final Max Damage was " & damMax & "..."
        Print "You lost to a " & enemy(nEnemy) & " with " & nDamageRoll & "HP..."

        WaitSeconds(timeSlow)

        Print "Game Over... Goodbye ☺"
        Print "-----------------------------------"

        WaitSeconds(timeCinematic)

        End

    End If

Next i


' --------------------------------------------
' BOSS ENCOUNTER
' --------------------------------------------

Dim As String enemyBoss(0 To 2)

enemyBoss(0) = "Spectre"
enemyBoss(1) = "Spriggan"
enemyBoss(2) = "Sphinx"

Dim As Integer nBoss = RandomRange(0, 3)
Dim As Integer bossHealth = RandomRange(444, 555)

Print "You find yourself at the top of the tower facing off against a " & enemyBoss(nBoss) & "..."
Print "You bravely attack it..."

WaitSeconds(timeCinematic)

' --------------------------------------------
' BOSS FIGHT
' --------------------------------------------

Dim As Integer nDamage = RandomRange(damMin, damMax)

If nDamage >= (nDamage * 0.5) Then

    Print "You deal " & nDamage & " damage to the " & enemyBoss(nBoss) & "..."

    WaitSeconds(timeCinematic)

Else

    Print "You do " & nDamage & " damage to the " & enemyBoss(nBoss) & ", you fear your journey is coming to an end..."

    WaitSeconds(timeCinematic)

End If

' --------------------------------------------
' BOSS RESULT
' --------------------------------------------

If nDamage > bossHealth Then

    Print "You have slayed the " & enemyBoss(nBoss) & " and have conquered the Tower!"

    WaitSeconds(timeCinematic)

Else

    Print "-----------------------------------"
    Print "You have fumbled your swing on Floor " & nFloor & " and got annihilated by a " & enemyBoss(nBoss) & ", RIP " & name & "..."
    Print "-----------------------------------"
    Print "☠   ☠   ☠   ☠   ☠   ☠   ☠   ☠"
    Print "-----------------------------------"

    WaitSeconds(timeSlow)

    Print "Your final Max Damage was " & damMax & "..."
    Print "You lost to a " & enemyBoss(nBoss) & " with " & bossHealth & "HP..."

    WaitSeconds(timeSlow)

    Print "Game Over... Goodbye ☺"
    Print "-----------------------------------"

    WaitSeconds(timeCinematic)

End If
