function Model() {

}
var Types = {
    SHIP: 'S',
};
Model.prototype.model = {};
Model.prototype.previousModel = {};

Model.prototype.clearModel = function() {
    this.previousModel = this.model;
    this.model = {};
}
Model.prototype.getShips = function() {
  if (!this.model[Types.SHIP]) {
      this.model[Types.SHIP] = [];
  }
  return this.model[Types.SHIP];
}
Model.prototype.getPreviousShips = function() {
  if (!this.previousModel[Types.SHIP]) {
      this.previousModel[Types.SHIP] = [];
  }
  return this.previousModel[Types.SHIP];
}
