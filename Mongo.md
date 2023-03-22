Remove a field
```
db.getCollection("journey").updateOne(
   { _id: "6414886c8ae21267bdb5b206" },
   { $unset: { surcharge: "" } }
)
```
